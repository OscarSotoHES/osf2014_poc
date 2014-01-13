Distributed Play Framework app. with cache.
===========


1. Présentation
-----------
  
  Le but de ce projet est de tester l'impact, au niveau des performances, de l'utilisation d'un système de cache au sein d'une application Play distribuée. 
  
  Pour réaliser les mesures nous avons réalisés les tests avec JMeter en utilisant un front-end serveur Apache dans lequel on utilise le système d'un load-balancer pour nos 2 instances de l'application. Cette application utilise le module de cache de Play. 
  
  On utilise le plugin [play2-memcached](https://github.com/mumoshu/play2-memcached) qui s'ajoute à Play via la configuration de l'application. Ce plugin Memcached lance un serveur qui tourne derrière notre application et devant la base de données. Cela nous permet d'avoir un système distribué de cache pour améliorer les performances de l'application en respectant les principes de "Reactive Manifesto".

2. Structure du projet
-----------

Pour faire les tests on a décidé d'utiliser un serveur Apache pour le front-end. Celui-ci nous permet d'accéder à un load-balancer pour les différentes instances. c'est ce service qui décide à quel noeud envoyer la requête qui arrive. Pour gérer le cache, on a décidé d'utiliser Memcached au lieu du plugin par défaut Ehcache car il ne permet pas la distribution du cache. Pour finir, on utilise un serveur MySql pour la base de données.

3. Réponse aux questions
-----------

* What is the performance impact of using a caching layer when implementing a REST API with Play?

Dans le cas d'une API REST il faut différencier deux situations différentes. 

En effet, le cache est utilisé que dans le cas de demande d'informations à la base de données. Dans notre structure, il s'agit des requêtes GET. Donc seulement les requêtes GET sont améliorées et selon le cas cela peut être la requête la plus utilisée ou pas. Si c'est le cas, le gain de performances est très important et comme le coût de la mise en cache est négligeable, implémenter un système de cache est bénéfique. Dans le cas où on utilise l'API presque exclusivement pour des opérations d'ajout, update ou suppression d'éléments la mise en cache ne propose aucun avantage.

Notre réponse fait la supposition que le cache est sécurisé et bien implémenté dans le sens que le temps d'expiration est optimal et quand le cache devient "dirty" le cache remet bien à jour l'information.


* How is it possible to use a caching layer in a cluster environment, when several Play “nodes” are setup to serve HTTP requests?

Pour pouvoir utiliser le cache dans un environnement distribué, il faut pourvoir mutualiser le cache. En effet, tous les nœuds doivent avoir la même information en cache pour maintenir l'intégrité des données retournés à l'utilisateur. Quand un des nœuds a besoin de mettre à jour ou éliminer un élément du cache tous les nœuds doivent avoir exactement la même information. C'est pour cela que la solution la plus efficiente et simple et d'utiliser un serveur de cache.

Dans notre projet ceci est fait grâce au server Memcached auquel chaque nœud se connecte. En effet, chaque instance connaît l'adresse réseau du server Memcached et se connecte automatiquement. C'est le serveur qui s'occupe de toutes les opérations, synchronisation, etc...

De cette façon, toutes les instances de l'API ont accès à un cache rapide, sûr et qui maintient l'intégrité des données. Forçément, pour ce dernier point, le développeur peut faire des erreurs et oublier de faire les opérations de cache qui rendent le cache faux.


4. Installation
-----------

Pour cette partie, on a fait le choix de ne pas décrire comment installer chaque serveur différent car cela 		dépend de l'environnement de l'utilisateur. Dans notre cas, on a eu besoin d'installer sous Windows et OS X, et 	comme les procédures sont assez différentes on préfère montrer les différents paramètres à mettre en place une 		fois les serveurs installés.

* Apache (Version 2.2.25)


	Dans le fichier httpd.conf il faut décommenter les lignes suivantes:
	
	```
	   LoadModule proxy_module modules/mod_proxy.so
	   LoadModule proxy_balancer_module modules/mod_proxy_balancer.so
	   LoadModule proxy_connect_module modules/mod_proxy_connect.so
	   LoadModule proxy_http_module modules/mod_proxy_http.so
	
	   Include conf/extra/httpd-vhosts.conf
	```

	Dans le fichier httpd-vhosts.conf il faut ajouter :

	```
	   <VirtualHost *:80>
		  ServerName localhost
		<Location /balancer-manager>
		  SetHandler balancer-manager
		  Order Deny,Allow
		  Deny from all
		  Allow from .localhost
		</Location>
		<Proxy balancer://mycluster>
		  BalancerMember http://localhost:9850
		  BalancerMember http://localhost:9851 status=+H
		</Proxy>
		<Proxy *>
		  Order Allow,Deny
		  Allow From All
		</Proxy>
		ProxyPreserveHost On
		ProxyPass /balancer-manager !
		ProxyPass / balancer://mycluster/
		ProxyPassReverse / http://localhost:9850/
		ProxyPassReverse / http://localhost:9851/
	  </VirtualHost>
	```

	Une fois la bonne configuration installée, il suffit de lancer le serveur Apache ou de le redémarrer.

* Memcached

	Pour le serveur de cache, il suffit de lancer memcached pour le port 11211 de cette façon :
	
	````
	memcached -d -p IP 11211
	```

* Play

	Pour ce projet, on a décidé d'utiliser deux instances de l'application. Pour les lancer, on a tout 			simplement copié l'application dans deux dossiers différents et on a utilisé la commande suivante 			avec 9850 et 9851 pour les ports (deux terminaux ou invites de commandes différent(e)s) :
	
	````
	play run PORT
	````

	Au sein de l'application on a utilisé un plugin pour utiliser Memcached. Au sein du groupe on a eu de problèmes 	pour l'utiliser car il utilise lui-même un autre plugin appelé spy-memcached. Normalement, grâce à Maven il est 	sensé de le télécharger mais des fois il ne le faisait pas. Par conséquent, il a fallu intégrer le plugin dans 		le système.
	

5. API
-----------

Cette API permet de gérer les citoyens des villes. Il a été créé dans le but de tester la performance des systèmes de caches. Que ce soit sur une application ou sur un système distribué. Il consiste de deux éléments. Des villes qui peuvent avoir 0 ou plusieurs citoyens et des citoyens qui doivent avoir une ville. Notre application utilise les routes suivants : 

```
# City
	GET                /city                                                controllers.Application.allCities()
	POST        /city                                                controllers.Application.addCity()
	DELETE        /city/:idc                                        controllers.Application.deleteCity(idc: Long)
	
	# Citizen
	GET                /citizen                                        controllers.Application.allCitizens()
	GET                /city/:idc/citizen                        controllers.Application.getCitizensOfCity(idc: Long)
	POST        /city/:idc/citizen                        controllers.Application.newCitizen(idc: Long)
	DELETE        /city/:idc/citizen/:id                controllers.Application.deleteCitizen(idc: Long, id: Long)
```

6. Tests avec client REST
-----------

asdsadas
