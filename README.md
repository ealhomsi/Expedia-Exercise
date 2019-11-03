# Expedia-Exercise
This is expedia interview exercise

## Backend Developed in JavaSpring
- I have chosen the first challenge.
- this backend is designed to be hosted for live production as a heroku app. 
- it uses prostgress sql for persistence 
- it uses travis.ci for CI/CD
- I have implemented a generalistic time difference finder when searching for flights. Therefore, it is possible to specify flights_within?distance=16 which meants find flights that are 16 hours away from the current time.

## Limitations
- I made the application to store the date in full detail ex: 11/2/2019 11:00 AM. Therefore, it would not work as in the example and the difference from todays date would be larger than just couple of hours.
- The git repository expose secrets in the applications properties and in the .travis file. This was done to finish the application within the time limit.
- I would have preferred ruby, python, c# for doing backend applications based on my previous internship experiences.
- I have written tests for the service. However, this is far from being complete.

Thank you for your time and consideration,
Sincerely,
Elias