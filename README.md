<p align="center">
<img src="https://www.google.com/search?q=https://i.imgur.com/S8a1x2G.png" alt="Smart Email Assistant Banner" width="700"/>
</p>
<h1 align="center">🚀 Smart Email Assistant - AI Chrome Extension</h1>
<p align="center">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Java-21-blue%3Flogo%3Dopenjdk%26logoColor%3Dwhite" alt="Java 21">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Spring_Boot-3-brightgreen%3Flogo%3Dspring%26logoColor%3Dwhite" alt="Spring Boot 3">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/React-18-blue%3Flogo%3Dreact%26logoColor%3D61DAFB" alt="React 18">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Vite-5-purple%3Flogo%3Dvite%26logoColor%3Dwhite" alt="Vite">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Gemini_API-Google-orange%3Flogo%3Dgoogle%26logoColor%3Dwhite" alt="Gemini API">
</p>
<p align="center">
A powerful Chrome extension that supercharges your email workflow using the Google Gemini API. This full-stack application features a robust Java Spring Boot backend and a sleek, modern React frontend, integrating directly into your browser and Gmail.
</p>

✨ Core Features
🤖 AI-Powered Composition: Generate complete emails from a simple prompt.
🧠 Intelligent Replies: Create context-aware replies to incoming emails.
📰 Summarize: Condense long email threads into quick, easy-to-read summaries.
✒️ Suggest Subject Lines: Generate catchy and appropriate subject lines based on the email body.
✍️ Rephrase & Improve: Enhance your writing for better clarity, tone, and impact.
🎨 Tone & Language Control: Instantly adjust the tone (e.g., Professional, Casual) and language of the generated text.
🌓 Sleek User Interface: A beautiful and intuitive UI with a fully functional Light/Dark Mode toggle.
📧 Direct Gmail Integration: Access the assistant from a button that appears directly in the Gmail compose window.
📸 Live Demo & Screenshot
Here's a look at the Smart Email Assistant in action!

🔧 How It Works
The project uses a client-server architecture:
Frontend (Chrome Extension): The React application runs in your browser, providing the UI. When you click a button, it sends your request to the backend.
Backend (Spring Boot Server): A local Java server receives the request, creates a specific prompt, and securely calls the Google Gemini API.
Response: The Gemini API's response is sent back to the server, which then relays it to the frontend to be displayed in the UI.

🛠️ Tech Stack
This project is built with a modern and powerful set of technologies:

Backend
Java 21, Spring Boot 3, Maven, Spring WebFlux (for API calls)

Frontend
React 18 (with Hooks), Vite, JavaScript (ES6+)
AI Model
Google Gemini API (gemini-1.5-flash-latest)
Styling
Tailwind CSS (for a utility-first, responsive design)
Icons
Lucide React (for clean and modern icons)
Platform
Google Chrome Extension (Manifest V3)

⚙️ Setup and Installation
Follow these steps to get the project running on your local machine.
Prerequisites
Java (JDK) 21
Apache Maven
Node.js (LTS version)
A Google Gemini API Key

1. Backend Setup
First, get the server running.
Navigate to the Backend Directory:
cd Email-Assistance

Configure API Key:
Open the src/main/resources/application.properties file.
Find the line gemini.api.key=YOUR_GEMINI_API_KEY_HERE.
Replace the placeholder with your actual Gemini API key.
Run the Application:
Open the project in your IDE (e.g., IntelliJ IDEA, VS Code).
Run the EmailAssistanceApplication.java file.
The backend will start and listen on http://localhost:8080.

2. Frontend Setup
Next, set up the Chrome Extension UI.
Navigate to the Frontend Directory:
cd ../email-assistant-frontend
Install Dependencies:
npm install
Build the Extension:
This command compiles the React code into static files Chrome can use.
npm run build
This will create a dist folder. This folder is your complete extension.

🚀 Loading the Extension in Chrome
Open your Google Chrome browser.
Go to chrome://extensions.
Enable "Developer mode" with the toggle in the top-right corner.
Click the "Load unpacked" button.
Select the dist folder that was created inside your email-assistant-frontend project.
The "Smart Email Assistant" icon will appear in your Chrome toolbar! (You may need to pin it from the puzzle-piece menu).

💡 How to Use
Make sure the backend is running! This is essential.
Popup Assistant: Click the extension icon in your toolbar to open the main UI.

Gmail Integration:
Open Gmail.
Click the "Compose" button.
You will see a new "Assistant ✨" button next to the "Send" button.

🔮 Future Enhancements
This project has a solid foundation. Here are some ideas for future development:
Direct Text Insertion: Make the Gmail button read the text from the compose box and insert the generated text back automatically.
Generation History: Save past generations to a database so users can access them later.
User Accounts: Implement authentication to keep user histories private.
