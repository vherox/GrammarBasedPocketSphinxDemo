<h5> Grammar-based PocketSphinx Demo</h5>
Simple speech recognition app using grammar-based search of PocketSphinx

<h5>Description</h5>
The application exemplifies how to perform speech recognition with PocketSphinx using only grammars as language model. 
The utterances that can be recognized are specified by the rules that appear on the file "app/src/assets/async/domain.gram".
Visually, the app consists of only one button that activates speech regonition when the user clicks on it. 
To test it, just click that button and say one of the utterances of the domain.gram file. A transcription of what you said will (hopefully) be shown below the button.

<h5>License</h5>
This app is entirely based on the PocketSphinx Android demo: https://github.com/cmusphinx/pocketsphinx-android-demo (see license below). The code to switch between phonetic, language model and grammar-based searches has been removed, as we just want to use grammar search here. Also, we have replaced the digits.gram by a more elaborated file called "domain.gram". 

Copyright (c) 2013-2015, Alpha Cephei Inc. All rights reserved.
 
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:
 
1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
 
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 





