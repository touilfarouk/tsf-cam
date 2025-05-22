const video = document.getElementById("webcam");
const liveView = document.getElementById("liveView");
const demosSection = document.getElementById("demos");
const enableWebcamButton = document.getElementById("webcamButton");

//Vérifiez si l'accès à la webcam est pris en charge.

function getUserMediaSupported() {
  return !!(navigator.mediaDevices && navigator.mediaDevices.getUserMedia);
}

/*Si la webcam est prise en charge, ajoutez un écouteur d'événement au 
 bouton lorsque l'utilisateur veut l'activer pour appeler la fonction enableCam
 que nous allons définir à l'étape suivante.
 */
if (getUserMediaSupported()) {
  enableWebcamButton.addEventListener("click", enableCam);
} else {
  console.warn("getUserMedia() is not supported by your browser");
}

// Activez la vue webcam en direct et lancez la classification.
function enableCam(event) {
  // Ne continuez que si le COCO-SSD a fini de se charger.
  if (!model) {
    return;
  }

  //Cachez le bouton une fois cliqué.
  event.target.classList.add("removed");

  // paramètres getUsermedia pour forcer la vidéo mais pas l'audio.
 const constraints = {
  video: {
    facingMode: { exact: "environment" }
  }
};

  // Activez le flux de la webcam.
  navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
    video.srcObject = stream;
    video.addEventListener("loadeddata", predictWebcam);
  });
}

var children = [];

function predictWebcam() {
  // Commençons maintenant à classer une image dans le flux.
  model.detect(video).then(function (predictions) {
    // Supprimez toute surbrillance que nous avons faite dans le cadre précédent.
    for (let i = 0; i < children.length; i++) {
      liveView.removeChild(children[i]);
    }
    children.splice(0);

    // Maintenant, parcourons les prédictions et dessinons-les dans la vue en direct si
    //ils ont un score de confiance élevé.
    for (let n = 0; n < predictions.length; n++) {
      // Si nous sommes sûrs à plus de 66 % d'être sûrs de l'avoir bien classé, dessinez-le !
      if (predictions[n].score > 0.66) {
        //predictions[n].class === 'person'
        const p = document.createElement("p");
        const h1 = document.createElement("h1");
        h1.setAttribute("class", "highlighter");
        h1.innerText =
          predictions.length +
          " elements de la même famille sonts détecté sur cette capture";
        p.innerText =
          predictions[n].class +
          " count" +
          n +
          " - with " +
          Math.round(parseFloat(predictions[n].score) * 100) +
          "% confidence.";
        /*if(n >= 2 ){
                alert('mamnou3 el tajamo3')
              }*/
        p.style =
          "margin-left: " +
          predictions[n].bbox[0] +
          "px; margin-top: " +
          (predictions[n].bbox[1] - 10) +
          "px; width: " +
          (predictions[n].bbox[2] - 10) +
          "px; top: 0; left: 0;";

        const highlighter = document.createElement("div");
        highlighter.setAttribute("class", "highlighter");
        highlighter.style =
          "left: " +
          predictions[n].bbox[0] +
          "px; top: " +
          predictions[n].bbox[1] +
          "px; width: " +
          predictions[n].bbox[2] +
          "px; height: " +
          predictions[n].bbox[3] +
          "px;";
        let i = 0;
        predictions.forEach((predictions) => {
          const x = predictions.bbox[0];
          const y = predictions.bbox[1];
          const width = predictions.bbox[2];
          const height = predictions.bbox[3];
        });
        liveView.appendChild(highlighter);
        liveView.appendChild(p);
        liveView.appendChild(h1);
        children.push(highlighter);
        children.push(p);
        children.push(h1);
      }
    }

    // Appelez à nouveau cette fonction pour continuer à prédire quand le navigateur est prêt.
    window.requestAnimationFrame(predictWebcam);
  });
}

// Store the resulting model in the global scope of our app.
var model = undefined;

// Before we can use COCO-SSD class we must wait for it to finish
// loading. Machine Learning models can be large and take a moment
// to get everything needed to run.
// Note: cocoSsd is an external object loaded from our index.html
// script tag import so ignore any warning in Glitch.
cocoSsd.load().then(function (loadedModel) {
  model = loadedModel;
  // Show demo section now model is ready to use.
  demosSection.classList.remove("invisible");
});
