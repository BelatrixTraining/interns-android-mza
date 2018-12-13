# interns-android-mza
Repository for Mendoza Android Interns - December 2018

In the second lesson we will learn about all the Andoid Components and we will review some examples for each component.

## Slides
https://docs.google.com/presentation/d/1_fznfydr5eg9ikankWTEcSGU09K1i0RRBTuPdONB72w/edit?usp=sharing

## Android Components Examples

## Activities and Intent
For Activities and Intent we have two examples:
- *TwoActivities*: This example shows how two activities can interact between them using explicit intents and also it shows how to return a result from an Activity to another.

- *ImplicitIntent*: This example shows how an Activity can use an implicit intent to open another application that can perform a required action.

## BroadcastReceiver
For Broadcast Receiver we have an example that notice when the screen is turn on and shows a message with the time for that action.

## Service
For services we have an example to invoke a Service to download an inage from url and return the result to the Activty usen LocalBroadcastManager.
Note: This example does not contain the implementation for RuntimePermissions so this has to be run in Android 5 devices or lower.
