
function doGet(e) {
  var template = HtmlService.createTemplateFromFile('index');
  template.map = e.parameter.map;
  Logger.log(e.parameter.map);
  return template.evaluate();
}

function getVariable(env) {
  if (env == "GRAPHOPPER_API_KEY") {
    return "your_graphhopper_api_key"; // Replace 'your_graphhopper_api_key' with your GraphHopper API key get it from https://www.graphhopper.com/
  }
  if (env == "GRAPHOPPER_URL") {
    return "https://graphhopper.com/api/1/route"
  }
  if (env == "SPREAD_SHEET_URL") {
    return "your_spreadsheet_url"; // Replace 'your_spreadsheet_url' with the URL of your Google Sheets file example: https://docs.google.com/spreadsheets/d/your_spreadsheet_id/edit
  }
}

function storeRoutes(placeDtos) {
  let homeUrl = ScriptApp.getService().getUrl();
  homeUrl = decodeURIComponent(homeUrl.replace(/\/$/, '')); 
  let spreadSheet = SpreadsheetApp.openByUrl(getVariable("SPREAD_SHEET_URL"));
  let placesByFileName = groupByFileName(placeDtos);
  let routesMaps = [];

  placesByFileName.forEach(placesArray => {
    let uuid = Utilities.getUuid();
    let places = [];
    let routes = [];
    let fileName;
    let distance = 0;
    let localDate = new Date().toLocaleString();

    let lastPlace;
    for (let i = 0; i < placesArray.length; i++) {
      let placeDto = placesArray[i];
      fileName = placeDto.fileName;
      places.push(placeDto);
      if (lastPlace) {
        let startPoint = `${lastPlace.latitude},${lastPlace.longitude}`;
        let endPoint = `${placeDto.latitude},${placeDto.longitude}`;
        let hopperResponse = getRoute(startPoint, endPoint);
        distance += hopperResponse.distance;
        let points = hopperResponse.points;
        for (let j = 0; j < points.length; j++) {
          let point = points[j];
          let route = { latitude: point[1], longitude: point[0] };
          routes.push(route);
        }
      }
      lastPlace = placesArray[i];
    }

    let sheet = spreadSheet.getSheets()[0];
    let lastRow = sheet.getLastRow() + 1;
    sheet.getRange(lastRow, 1).setValue(uuid);
    sheet.getRange(lastRow, 2).setValue(fileName);
    sheet.getRange(lastRow, 3).setValue(localDate);
    sheet.getRange(lastRow, 4).setValue(distance);
    sheet.getRange(lastRow, 5).setValue(JSON.stringify(places));
    let routesString = JSON.stringify(routes);
    let routesArray = [];
    for (let i = 0; i < routesString.length; i += 50000) {
      routesArray.push(routesString.substring(i, i + 50000));
    }
    for (let i = 0; i < routesArray.length; i++) {
      sheet.getRange(lastRow, 6 + i).setValue(routesArray[i]);
    }
    routesMaps.push({ uuid: uuid, fileName: fileName , url: `${homeUrl}?map=${uuid}`});
  });
  return JSON.stringify(routesMaps);
}

function getRoute(start, end) {
  const routeMachineUrl = getVariable("GRAPHOPPER_URL");
  const apiKey = getVariable("GRAPHOPPER_API_KEY");
  const url = `${routeMachineUrl}?point=${start}&point=${end}&vehicle=car&locale=en&key=${apiKey}&instructions=false&points_encoded=false`;
  let response = UrlFetchApp.fetch(url);
  let data = JSON.parse(response.getContentText());
  let paths = data.paths[0];
  let distance = paths.distance;
  let points = paths.points.coordinates;
  return {
    distance: distance,
    points: points
  }
}

function groupByFileName(placeDtos) {
  let placesByFileName = new Map();
  for (let i = 0; i < placeDtos.length; i++) {
    let fileName = placeDtos[i].fileName;
    let placesList = placesByFileName.get(fileName) || [];

    placesList.push(placeDtos[i]);
    placesByFileName.set(fileName, placesList);
  }
  return placesByFileName;
}


function getMap(uuid) {
  let spreadSheet = SpreadsheetApp.openByUrl(getVariable("SPREAD_SHEET_URL"));
  let sheet = spreadSheet.getSheets()[0];
  let row;
  try {
    row = sheet.createTextFinder(uuid).findNext().getRow();
  } catch (error) {
    return JSON.stringify({ error: "No se encontro el mapa" });
  }
  let id = sheet.getRange(row, 1).getValue();
  let fileName = sheet.getRange(row, 2).getValue();
  let date = sheet.getRange(row, 3).getValue();
  let distance = sheet.getRange(row, 4).getValue();
  let places = sheet.getRange(row, 5).getValue();
  let routes = "";
  let i = 6;
  let routesString = "";
  let routesStringAux = sheet.getRange(row, i).getValue();
  while (routesStringAux) {
    routesString += routesStringAux;
    i++;
    routesStringAux = sheet.getRange(row, i).getValue();
  }
  routes = JSON.parse(routesString);
  return JSON.stringify({ id: id, fileName: fileName, date: date, distance: distance, places: JSON.parse(places), routes: routes });
}



