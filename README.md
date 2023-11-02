# Time Tracker - Employee Productivity Monitoring

Time Tracker is a powerful application designed to help employers track their employees' productivity in a non-intrusive and transparent manner. This application monitors the applications opened by employees on their desktop, records the time they spend on those applications, and takes timely screenshots of the system. To enhance web browser activity tracking, it requires a companion web browser extension. If the extension is not detected while a web browser is in use, the application shuts down to encourage employees to install the extension.

**Please note that Time Tracker does not secretly gather personal information**. It solely reads the names of open applications and files, which are obtained from the titles of the open application windows. The application runs visibly and provides real-time information through a tray widget, indicating that it is active and displaying the time it has been running.

## Browser Extension

To enhance web browser activity tracking, Time Tracker requires a companion web browser extension. The extension operates in a non-intrusive manner by reading only the URL of the active and focused browser tab. This information is sent to the Time Tracker application to provide a comprehensive view of the employee's activities.

- **Companion Browser Extension Repository**: [Time Tracker Extension Repository](https://github.com/yourorganization/timetracker-extension)

Please visit the extension repository for installation instructions and additional details. The extension is available for popular web browsers, ensuring seamless compatibility with your preferred browser. It works hand-in-hand with the Time Tracker desktop application to provide a complete monitoring solution while respecting user privacy.


## Features

- Application usage tracking: Monitor the applications employees use and how much time they spend on them.
- Screenshot capture: Capture periodic screenshots to provide visual context for the work being done.
- Web browser activity tracking: Requires a companion web browser extension to track web browser activities.
- User-friendly tray widget: Display real-time information about the application's activity.
- Transparent and non-intrusive: Employees are aware of the monitoring, and no personal data is collected.

## Technology Stack

Time Tracker is built using the following technologies:

- **Spring Boot**: Provides web features, API endpoints, and integration with the web browser extension. Also offers dependency injection and JPA for efficient application development.

- **Kotlin Compose Desktop**: Used for creating the desktop application interface with a focus on user-friendly and interactive UI.

## Getting Started

Follow these steps to get started with Time Tracker:

1. Clone the Time Tracker repository to your local machine.
2. Install the necessary dependencies and configure the database settings.
3. Build and run the Spring Boot application.
4. Install the companion web browser extension on the employees' browsers.

## Community Development Guidelines

I welcome contributions from the community to help improve Time Tracker. To contribute, follow these guidelines:

- Fork the repository and create a new branch for your feature or bug fix.
- Maintain a clean and consistent code style.
- Write clear and comprehensive documentation for your changes.
- Make sure your code passes all tests.
- Create a pull request and provide a detailed description of the changes you made.

## License

Time Tracker is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues or have questions, please feel free to reach out to [Me](mailto: bnnsz384@gmail.com).