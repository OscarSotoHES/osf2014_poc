name := "mysuperwebapp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings

libraryDependencies ++= Seq( 
	"com.github.mumoshu" %% "play2-memcached" % "0.3.0.2",
	"mysql" % "mysql-connector-java" % "5.1.18" )
