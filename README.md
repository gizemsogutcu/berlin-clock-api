# Berlin Clock API  

*Berlin clock api is a spring boot application for calculate berlin clock.*

Api has two endpoints:  
  

 - **/api/berlinClock?time=12:29:25** represents Berlin Clock calculation result, time is not mandatory.  

 - **api/berlinClock** represents Berlin Clock calculation result via current time.  
  
  Api can be tested with swagger. If the project starts localhost and port of 8080 the link is accessible(http://localhost:8080/swagger-ui.html)

## Response

  

    {  
      "success": true,  
      "errorMessage": null,  
      "body": "O RRRR ROOO YYROOOOOOOO OOOO"  
    }  

  

 - **success:** if service returned response(no exception thrown) success is true, when any exception thrown success is false.
 - **errorMessage:** if service throws exception errorMessage loaded, when any exception thrown errorMessage is null.
 - **body:** berlin clock result.

|Berlin Clock Lamps Definitions|  |
|------------------------------|--|
|             O                 | Lamp is off |
|             R                | Lamp's light is red|
|             Y                | Lamp's light is yellow|

