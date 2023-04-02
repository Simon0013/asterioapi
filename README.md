# Web API for AsterioSoft

This is backend API for management advertising categories and text banners.

## Before starting

Your PC must have installed MySQL JDBC driver and JRE.

## For start

Download source code from this repository. Then, more likely, you will have to change parametrs in `application.properties` file in folder `src/main/resourses` to actual ones. In your MySQL execute `tables.sql` script.
If you have any Java IDE, you can open the project there and run.
Else open command line, where you have to navigate to root of the project and execute command

```
mvn spring-boot:run
```

After that the application execute in 8080 port, and you can send requests there.

## Requests

### Banners

For get a banner by id, use a GET request like this:

```
localhost:8080/bid?id={id}
```

For get a banner by categories IDs, use a GET request like this:

```
localhost:8080/bid?cat={cat1}&cat={cat2}&cat={cat3}&...
```

For create a new banner, use a POST request like this:

```
localhost:8080/bid/
```

and send this response body:

```
{
	name: YOUR_BANNER_NAME;
	banner_text: YOUR_BANNER_TEXT;
	price: YOUR_BANNER_PRICE
}
```

For update an exist banner, use a PUT request like this:
```
localhost:8080/bid?id={id}
```

and send this response body:

```
{
	name: YOUR_BANNER_NAME;
	banner_text: YOUR_BANNER_TEXT;
	price: YOUR_BANNER_PRICE
}
```

For delete an exist banner, use a DELETE request like this:
```
localhost:8080/bid?id={id}
```

### Categories

For get a category by id, use a GET request like this:

```
localhost:8080/cat?id={id}
```

For get a category by banners IDs, use a GET request like this:

```
localhost:8080/cat?bid={bid1}&cat={bid2}&cat={bid3}&...
```

For create a new category, use a POST request like this:

```
localhost:8080/cat/
```

and send this response body:

```
{
	name: YOUR_CATEGORY_NAME;
	request_id: YOUR_CATEGORY_REQUEST_ID
}
```

For update an exist category, use a PUT request like this:
```
localhost:8080/cat?id={id}
```

and send this response body:

```
{
	name: YOUR_CATEGORY_NAME;
	request_id: YOUR_CATEGORY_REQUEST_ID
}
```

For delete an exist category, use a DELETE request like this:
```
localhost:8080/cat?id={id}
```

### Log records

For get a log record by id, use a GET request like this:

```
localhost:8080/log?id={id}
```

For delete an exist log record, use a DELETE request like this:
```
localhost:8080/cat?id={id}
```