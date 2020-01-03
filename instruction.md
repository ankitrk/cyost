to add new file via git bash:
(navigate to folder...)
git init (to create new repository)
git config user.email "abc@xyz.com"
git config user.name "abc"
git remote add origin <link on github>
git add <subfolder directory name>
git commit -m "comment"
git push -u origin master

HeartsApp setup


    Prerequisite:

Install Node >11
Git
Android studio 3.4.2
    

    Setup
Create a folder heartsapp
cd heartsapp 
Git clone git@github.com:HeartfulnessInstitute/heartsapp-android.git
Git clone git@github.com:HeartfulnessInstitute/heartsapp-react-native.git
Cd heartsapp-react-native
Npm install
Run:   react-native bundle --platform android --dev false --entry-file index.js --bundle-output ../heartsapp-android/app/src/main/assets/index.android.bundle --assets-dest ../heartsapp-android/app/src/main/res
Open heartsapp-android using android studio
