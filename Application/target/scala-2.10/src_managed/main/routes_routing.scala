// @SOURCE:/Users/jean-lucpasquier/Documents/workspace/osf_poc/conf/routes
// @HASH:7c21fd709f73d6221a82c9aae494190251396d53
// @DATE:Sat Jan 11 02:27:10 CET 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Application_allCities1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("city"))))
        

// @LINE:10
private[this] lazy val controllers_Application_addCity2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("city"))))
        

// @LINE:11
private[this] lazy val controllers_Application_deleteCity3 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("city/"),DynamicPart("idc", """[^/]+""",true))))
        

// @LINE:14
private[this] lazy val controllers_Application_allCitizens4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("citizen"))))
        

// @LINE:15
private[this] lazy val controllers_Application_getCitizensOfCity5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("city/"),DynamicPart("idc", """[^/]+""",true),StaticPart("/citizen"))))
        

// @LINE:16
private[this] lazy val controllers_Application_newCitizen6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("city/"),DynamicPart("idc", """[^/]+""",true),StaticPart("/citizen"))))
        

// @LINE:17
private[this] lazy val controllers_Application_deleteCitizen7 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("city/"),DynamicPart("idc", """[^/]+""",true),StaticPart("/citizen/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:21
private[this] lazy val controllers_Assets_at8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """city""","""controllers.Application.allCities()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """city""","""controllers.Application.addCity()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """city/$idc<[^/]+>""","""controllers.Application.deleteCity(idc:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """citizen""","""controllers.Application.allCitizens()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """city/$idc<[^/]+>/citizen""","""controllers.Application.getCitizensOfCity(idc:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """city/$idc<[^/]+>/citizen""","""controllers.Application.newCitizen(idc:Long)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """city/$idc<[^/]+>/citizen/$id<[^/]+>""","""controllers.Application.deleteCitizen(idc:Long, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Application_allCities1(params) => {
   call { 
        invokeHandler(controllers.Application.allCities(), HandlerDef(this, "controllers.Application", "allCities", Nil,"GET", """ City""", Routes.prefix + """city"""))
   }
}
        

// @LINE:10
case controllers_Application_addCity2(params) => {
   call { 
        invokeHandler(controllers.Application.addCity(), HandlerDef(this, "controllers.Application", "addCity", Nil,"POST", """""", Routes.prefix + """city"""))
   }
}
        

// @LINE:11
case controllers_Application_deleteCity3(params) => {
   call(params.fromPath[Long]("idc", None)) { (idc) =>
        invokeHandler(controllers.Application.deleteCity(idc), HandlerDef(this, "controllers.Application", "deleteCity", Seq(classOf[Long]),"DELETE", """""", Routes.prefix + """city/$idc<[^/]+>"""))
   }
}
        

// @LINE:14
case controllers_Application_allCitizens4(params) => {
   call { 
        invokeHandler(controllers.Application.allCitizens(), HandlerDef(this, "controllers.Application", "allCitizens", Nil,"GET", """ Citizen""", Routes.prefix + """citizen"""))
   }
}
        

// @LINE:15
case controllers_Application_getCitizensOfCity5(params) => {
   call(params.fromPath[Long]("idc", None)) { (idc) =>
        invokeHandler(controllers.Application.getCitizensOfCity(idc), HandlerDef(this, "controllers.Application", "getCitizensOfCity", Seq(classOf[Long]),"GET", """""", Routes.prefix + """city/$idc<[^/]+>/citizen"""))
   }
}
        

// @LINE:16
case controllers_Application_newCitizen6(params) => {
   call(params.fromPath[Long]("idc", None)) { (idc) =>
        invokeHandler(controllers.Application.newCitizen(idc), HandlerDef(this, "controllers.Application", "newCitizen", Seq(classOf[Long]),"POST", """""", Routes.prefix + """city/$idc<[^/]+>/citizen"""))
   }
}
        

// @LINE:17
case controllers_Application_deleteCitizen7(params) => {
   call(params.fromPath[Long]("idc", None), params.fromPath[Long]("id", None)) { (idc, id) =>
        invokeHandler(controllers.Application.deleteCitizen(idc, id), HandlerDef(this, "controllers.Application", "deleteCitizen", Seq(classOf[Long], classOf[Long]),"DELETE", """""", Routes.prefix + """city/$idc<[^/]+>/citizen/$id<[^/]+>"""))
   }
}
        

// @LINE:21
case controllers_Assets_at8(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     