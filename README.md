Distributed Play Framework app. with cache.
===========


1. Présentation
-----------
  
  Le but de ce projet est de tester l'impact, au niveau des performances, de l'utilisation d'un système de cache au sein d'une application Play distribuée. 
  
  Pour réaliser les mesures nous avons réalisés les tests avec JMeter en utilisant un front-end serveur Apache dans lequel on utilise le système d'un load-balancer pour nos 2 instances de l'application. Cette application utilise le module de cache de Play. 
  
  On utilise le plugin [play2-memcached](https://github.com/mumoshu/play2-memcached) qui s'ajoute à Play via la configuration de l'application. Ce plugin Memcached lance un serveur qui tourne derrière notre application et devant la base de données. Cela nous permet d'avoir un système distribué de cache pour améliorer les performances de l'application en respectant les principes de "Reactive Manifesto".

2. Documentation
-----------

3. Installation
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
	

4. API
-----------

5. Tests avec client REST
-----------


