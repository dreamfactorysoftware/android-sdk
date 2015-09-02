This directory contains the package file for importing the sample app into your DreamFactory instance. It includes the app record, schema, and sample data. You must go to the Applications tab in the admin app and import this file before you can run the sample app in Android Studio.

add_android.dfpkg - A special zip archive containing everything needed to create and run the app. This file can be imported directly into the Applications section of the admin app. Importing a package file always creates a new app.

The package file is a zip of the following files.

description.json - Application properties to be used for creating a new application when importing the app as a package file. This file is required.

data.json - Sample table rows to be created when importing the app as a package file. This file is optional.

schema.json - Application schema to be created when importing the app as a package file. This file is optional.