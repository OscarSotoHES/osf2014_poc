Open Source Framework POC
===========

Distributed Play Framework application with cache.
-----------

1. Présentation
  
  Le but de ce projet est de tester l'impact, au niveau des performances, de l'utilisation d'un système de cache au sein d'une application Play distribuée. 
  
  Pour réaliser les mesures nous avons réalisés les tests avec JMeter en utilisant un front-end Apache dans lequel on utilise le load-balancer pour 2 instances de l'application. Cette applicaiton utilise le module de cache de Play. 
  
  On utilise le module [play2-memcached](https://github.com/mumoshu/play2-memcached) pour se connecter à un server Memcached qui tourne sur le server. Cela nous permet d'avoir un système distribué de cache pour améliorer les performances de l'application en respectant les principes de "Reactive Manifesto".

2. Documentation

3. Installation

	* Apache
	
		Dans le fichier httpd-vhosts.conf il faut ajouter :
	
		```
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

		Une fois la bonne configuration de faite, il suffit de lancer le server.
	
	* Memcached

		Pour le server de cache il suffit de lancer memcached de cette façon :
		
		````
		memcached -d -p IP 11211
		```
	
	* Play

		Pour ce projet on a décidé d'utiliser deux instances de l'application. Pour les lancer on a tout 				simplement copier l'application dans deux dossiers différents et en utilisant la commande suivante 			avec 9850 et 9851 pour les ports :
		
		````
		play run PORT
		````
	

3. API

4. Tests avec client REST

3. API

4. Tests avec client REST


