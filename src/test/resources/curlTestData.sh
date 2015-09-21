#Add a new item via the rest interface
curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/ConferenceSchedule-API/rest/tags -d '{"tagname":"from curl"}'

#Update a specific item with ID...
curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8080/ConferenceSchedule-API/rest/tags/17 -d '{"tagname":"update from curl", "id":"17"}' -v
