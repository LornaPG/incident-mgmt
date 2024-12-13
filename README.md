# incident-mgmt

This is a simple incident management application. Users can add, delete, update, and list incidents.

### Instructions for local testing with Postman:
**Security login**
1. Create a POST request with url: http://localhost:8980/login.
2. Set body in "x-www-form-urlencoded" format.
3. Add parameters: 
     
    username: user

    password: password

**API testing example**
1. Create a POST request with url: http://localhost:8980/api/incidents/create.
2. Set body in raw json format:
    ````
   {
   "id":"id1",
   "title":"title1",
   "body":"body1"
   }
   ````