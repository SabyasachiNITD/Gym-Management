Created all the necssary APIS mentioned in the assignment.
Created as a web application based on Rest
To run this, please clone in and open in intellij and start the application
The apis are as below:

1.http://localhost:8080/bookings/gymclass - post
    
    Request Body Format:
    {
        "classId":126,
        "className": "cardio Class",
        "startDate":"06-JAN-2024",
        "endDate": "06-JAN-2029",
        "startTime": "10:15",
        "duration":30,
        "capacity": 10
    }
    
    Output Format

    {
        "classId": 126,
        "className": "cardio Class",
        "startDate": "2024-01-06",
        "endDate": "2029-01-06",
        "startTime": "10:15:00",
        "duration": 30,
        "capacity": 10
    }

2.http://localhost:8080/bookings/ - post

    Request Body Format

    {
        "classId":102,
        "memberName":"Sabyasachi",
        "participationDate":"27-JAN-2024"
    }

    Output Format

    {
        "classId": 126,
        "memberName": "Sabyasachi",
        "participationDate": "2024-01-27"
    }
3.http://localhost:8080/bookings/{member} - GET - 
Accepts member name as PathVariable and also accepts Request Parameter As Date
The Date format is dd-MMM-yyyy
The date request parameter is **optional** here.
    
    i. **Request Format 1**
        http://localhost:8080/bookings/Sabyasachi

    Output Format 

    [
        {
            "classId": 126,
            "memberName": "Sabyasachi",
            "participationDate": "2024-01-27"
        },
        {
            "classId": 126,
            "memberName": "Sabyasachi",
            "participationDate": "2024-01-28"
        },
        {
            "classId": 126,
            "memberName": "Sabyasachi",
            "participationDate": "2024-01-29"
        }
    ]

    ii. **Request Format Two**
        http://localhost:8080/bookings/Ritesh?date=28-JAN-2024
    [
        {
            "classId": 126,
            "memberName": "Ritesh",
            "participationDate": "2024-01-28"
        },
        {
            "classId": 126,
            "memberName": "Ritesh",
            "participationDate": "2024-01-28"
        },
        {
            "classId": 125,
            "memberName": "Ritesh",
            "participationDate": "2024-01-28"
        },
        {
            "classId": 124,
            "memberName": "Ritesh",
            "participationDate": "2024-01-28"
        },
        {
            "classId": 124,
            "memberName": "Ritesh",
            "participationDate": "2024-01-28"
        }
    ]
    

4.http://localhost:8080/bookings?date={dd-MMM-yyyy} GET - accepts Date as Request parameter

    Request Format: http://localhost:8080/bookings?date=29-JAN-2024

    Output Format:
    [
        {
            "classId": 126,
            "memberName": "Sasmit",
            "participationDate": "2024-01-29"
        },
        {
            "classId": 126,
            "memberName": "Sabya",
            "participationDate": "2024-01-29"
        },
        {
            "classId": 126,
            "memberName": "Ritesh",
            "participationDate": "2024-01-29"
        }
    ]