# URL Shortener Service

A simple URL shortener service built with Java and Spring Boot.

## Installation

1. Clone this repository: `git clone <your-repo-url>`.
2. Navigate into the directory: `cd url-shortener-service`.
3. Install dependencies: `<command to install dependencies>`.
4. Start the server: `<command to start the server>`.

## Usage

Send a POST request to `/shorten-url` with the following JSON body:

```json
{
    "url": "<your-long-url>"
}
```


Project Overview
The URL Shortener Service is a backend application that allows users to shorten URLs. This is particularly useful when dealing with long URLs that are hard to share or remember. The service provides a simple API that takes a long URL and returns a shortened version.

Technologies Used
Java: The main programming language used in the project.
Spring Boot: Used to create stand-alone, production-grade Spring-based Applications.
API Endpoints
POST /shorten-url
This endpoint is used to create a shortened URL. The request body should contain a JSON object with a single field url containing the URL to be shortened.


{
"url": "https://example.com/very/long/url"
}

{
"shortUrl": "http://your-domain.com/abcd1234"
}
