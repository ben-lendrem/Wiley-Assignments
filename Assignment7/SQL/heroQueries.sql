USE herodb;

SELECT hero.*, location.*
FROM hero JOIN sightings ON hero.heroID = sightings.heroID
JOIN location ON location.locationID = sightings.locationID
WHERE DATE(sightings.dateOfSighting) = '2022-07-21';