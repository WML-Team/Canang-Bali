# Deploy ML on Cloud
## VM on GCE
* create vm instance in Google Cloud Engine
* name the instance with canangbali1
* choose region us-central1 zone us-central1-a
* choose machine type n1-standard1
* choose boot disk ubuntu20.10
* in Firewall, check allow HTTP traffic and HTTP traffic

## Storage on Google Cloud Storage
* create bucket in Google Cloud Storage
* name the bucket with ml-canangbali1, in this bucket we put our machine learning model and it is required in the next deployment in ssh
* choose location type multi-region and choose us as the location
* choose standard for the default storage class
* choose Fine-grained for control access to objects

## Deploy on SSH
open SSH in the canangbali1 instance
```bash
$ gcloud auth login
$ gcloud config set project ageless-talent-313210
$ gsutil cp -r gs//ml-canangbali1 .
$ sudo apt-get update
$ sudo apt-get install python3.8 python3-pip
$ cd ml-canangbali1
$ sudo pip3 install -r requirements.txt
$ sudo python3 main.py
$ sudo python3 main.py &
```
after finished the deployment on ssh, we can use the external ip as an API and pass it to the android team

# Android path
To use damage detection feature, we use the server's URL that we got from Cloud Computing team. If you want to use our android source code, you can setup your own server instead of using our server (exclude the Bangkit 2021 Program evaluation team). Beside that we also use Firebase on our application backend.
You can find the APK on this link: https://github.com/WML-Team/Canang-Bali-App/tree/main/CanangBali/app/release
The following features that we used on our Android Deployment are:
* Firebase
* POST image to Cloud Server
* GET the response from Cloud Server
