# Places Maps 🗺️

## Description
Places Maps is a versatile tool designed to streamline the process of visualizing geographical coordinates stored in Excel files. This project efficiently processes input data, generates optimal routes based on the coordinates, and constructs a dynamic map visualization. The generated map links enable users to seamlessly explore the mapped routes. Places Maps can be deployed in various environments, offering flexibility to users. It can be deployed as a Java application with the backend powered by Quarkus, accompanied by a static frontend website. Alternatively, it can be deployed using Google Apps Script, providing integration with Google services for enhanced functionality.

## Key Features
- Excel data processing: Import geographical coordinates from Excel files. 📊
- Route generation: Compute optimized routes based on the provided coordinates. 🛣️
- Dynamic map visualization: Generate interactive maps showcasing the computed routes. 🌐
- Flexible deployment options: Deploy as a Java application with Quarkus backend or utilize Google Apps Script for deployment. 🚀

## Technologies Used
- Backend: Java with Quarkus
- Frontend: Static website only HTML, CSS, and JavaScript
- Deployment: Java deployment or Google Apps Script integration

## Usage Scenario
Imagine a logistics company needing to optimize delivery routes. Places Maps simplifies this process by importing coordinates from Excel, generating efficient routes, and providing a visual representation of the optimized delivery paths, aiding in better decision-making and resource allocation.

## Note
For detailed usage instructions and deployment options, please refer to the project documentation.

## Deployment with Google Apps Script 🚀

To deploy Places Maps using Google Apps Script, follow these steps:

1. **Clone the Repository:** 
   `git clone https://github.com/BookmanTasty/PlacesMaps`

2. **Navigate to the `distAppsScript` Directory:**
   `cd PlacesMaps/distAppsScript`

3. **Deploy Files to Google Apps Script:**
   - In the `distAppsScript` directory, you will find the `code.gs` and `index.js` files. 
   - These files need to be deployed in Google Apps Script.

4. **Modify the `code.gs` File:**
   - Open the `code.gs` file and modify the `getVariable(env)` function as follows:

   ```javascript
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
    ```
5. **Deploy as Google Apps Script:**
   - After modifying the code.gs file, deploy the script and index.html file in Google Apps Script.

6. **Accessing the Application:**
   - Once deployed, you can access the Places Maps application through Google Apps Script.
    - For detailed instructions on deploying with Google Apps Script, refer to the project documentation.

