// @SOURCE:/Users/jean-lucpasquier/Documents/workspace/osf_poc/conf/routes
// @HASH:7c21fd709f73d6221a82c9aae494190251396d53
// @DATE:Sat Jan 11 02:27:10 CET 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:21
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:21
class ReverseAssets {
    

// @LINE:21
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:10
def addCity(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "city")
}
                                                

// @LINE:14
def allCitizens(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "citizen")
}
                                                

// @LINE:16
def newCitizen(idc:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "city/" + implicitly[PathBindable[Long]].unbind("idc", idc) + "/citizen")
}
                                                

// @LINE:9
def allCities(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "city")
}
                                                

// @LINE:17
def deleteCitizen(idc:Long, id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "city/" + implicitly[PathBindable[Long]].unbind("idc", idc) + "/citizen/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:15
def getCitizensOfCity(idc:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "city/" + implicitly[PathBindable[Long]].unbind("idc", idc) + "/citizen")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:11
def deleteCity(idc:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "city/" + implicitly[PathBindable[Long]].unbind("idc", idc))
}
                                                
    
}
                          
}
                  


// @LINE:21
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:21
class ReverseAssets {
    

// @LINE:21
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:10
def addCity : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addCity",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "city"})
      }
   """
)
                        

// @LINE:14
def allCitizens : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.allCitizens",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "citizen"})
      }
   """
)
                        

// @LINE:16
def newCitizen : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.newCitizen",
   """
      function(idc) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "city/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idc", idc) + "/citizen"})
      }
   """
)
                        

// @LINE:9
def allCities : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.allCities",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "city"})
      }
   """
)
                        

// @LINE:17
def deleteCitizen : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteCitizen",
   """
      function(idc,id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "city/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idc", idc) + "/citizen/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:15
def getCitizensOfCity : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getCitizensOfCity",
   """
      function(idc) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "city/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idc", idc) + "/citizen"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:11
def deleteCity : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteCity",
   """
      function(idc) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "city/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idc", idc)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:21
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:21
class ReverseAssets {
    

// @LINE:21
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:10
def addCity(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addCity(), HandlerDef(this, "controllers.Application", "addCity", Seq(), "POST", """""", _prefix + """city""")
)
                      

// @LINE:14
def allCitizens(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.allCitizens(), HandlerDef(this, "controllers.Application", "allCitizens", Seq(), "GET", """ Citizen""", _prefix + """citizen""")
)
                      

// @LINE:16
def newCitizen(idc:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.newCitizen(idc), HandlerDef(this, "controllers.Application", "newCitizen", Seq(classOf[Long]), "POST", """""", _prefix + """city/$idc<[^/]+>/citizen""")
)
                      

// @LINE:9
def allCities(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.allCities(), HandlerDef(this, "controllers.Application", "allCities", Seq(), "GET", """ City""", _prefix + """city""")
)
                      

// @LINE:17
def deleteCitizen(idc:Long, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteCitizen(idc, id), HandlerDef(this, "controllers.Application", "deleteCitizen", Seq(classOf[Long], classOf[Long]), "DELETE", """""", _prefix + """city/$idc<[^/]+>/citizen/$id<[^/]+>""")
)
                      

// @LINE:15
def getCitizensOfCity(idc:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getCitizensOfCity(idc), HandlerDef(this, "controllers.Application", "getCitizensOfCity", Seq(classOf[Long]), "GET", """""", _prefix + """city/$idc<[^/]+>/citizen""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:11
def deleteCity(idc:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteCity(idc), HandlerDef(this, "controllers.Application", "deleteCity", Seq(classOf[Long]), "DELETE", """""", _prefix + """city/$idc<[^/]+>""")
)
                      
    
}
                          
}
        
    