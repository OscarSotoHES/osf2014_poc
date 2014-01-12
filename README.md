Open Source Framework POC
===========

Distributed Play Framework application with cache.
-----------

1) Présentation
  
  Le but de ce projet est de tester l'impact, au niveau des performances, de l'utilisation d'un système de cache au sein d'une application Play distribuée. 
  
  Pour réaliser les mesures nous avons réalisés les tests avec JMeter en utilisant un front-end Apache dans lequel on utilise le load-balancer pour 2 instances de l'application. Cette applicaiton utilise le module de cache de Play. 
  
  On utilise le module https://github.com/mumoshu/play2-memcached pour se connecter à un server Memcached qui tourne sur le server. Cela nous permet d'avoir un système distribué de cache pour améliorer les performances de l'application en respectant les principes de "Reactive Manifesto".

2) Documentation

3) Installation

	a) Apache
	
	b) Memcached
	
	c) Play
	

3) API

4) Tests avec client REST

3) API

4) Tests avec client REST


