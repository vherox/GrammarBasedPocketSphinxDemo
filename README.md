<h5> Grammar-based PocketSphinx Demo</h5>
Simple speech recognition app using grammar-based search of PocketSphinx

<h5>Description</h5>
The application exemplifies how to perform speech recognition with PocketSphinx using only grammars as language model. 
The utterances that can be recognized are specified by the rules that appear on the file "app/src/assets/async/domain.gram".
Visually, the app consists of only one button that activates speech regonition when the user clicks on it. 
To test it, just click that button and say one of the utterances of the domain.gram file. A transcription of what you said will (hopefully) be shown below the button.

<h5>License</h5>
This app is entirely based on the PocketSphinx Android demo (https://github.com/cmusphinx/pocketsphinx-android-demo). 
The code to switch between phonetic, language model and grammar-based searches has been removed, as we just want to use grammar search here. 
Also, we have replaced the digits.gram by a more elaborated file called "domain.gram". 

 





