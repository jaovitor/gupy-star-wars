# Gupy Star Wars

### Building and running

#### Launching the API
There's 2 ways to launch the API:

Within your favorite IDE: search for the class `io.gupy.sw.StarWarsApplication` and run it

Or just build, then run executable jar:

    mvn clean install package (tests will run automatically, if you don't want to test use the option -DskipTests)
    java -jar target/gupy-star-wars.jar

When the API is launched, Swagger documentation is made available at [http://localhost/swagger-ui.html](http://localhost/swagger-ui.html)


To deploy application build using the following command:

	mvn clean install package
	
And use the generated jar

	target/gupy-star-wars.jar
