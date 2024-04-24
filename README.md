# A simple HTTP API

JSON HTTP API for a minimalist job board application.

## Usage

### GET

Returns a map of open positions in the job board.

**URL :** localhost:8080/jobs

### POST

Inserts a new open position in the job board and returns the updated map.

**URL :** localhost:8080/jobs

**Body Example :**

```json
{
	"company": "Exoscale",
	"title": "Software Engineer",
	"location": "Remote"
}
```

### DELETE

Removes an open position from the job board and returns the updated map.

**URL :** localhost:8080/jobs/<Job's UUID>

**URL Example :** localhost:8080/jobs/fa9d3762-6848-43c8-8c1a-1a5eb0b7fb2a

## Sources

- https://medium.com/pragmatic-programmers/define-the-routes-with-rei-tit-aeb87224a6e8
- https://medium.com/@kalpanibhagya-kb/rest-apis-in-clojure-101-44fd191d4f97

