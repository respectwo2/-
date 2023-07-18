( async() => { const json = {
  "elements": [
    {
      "type": "tagbox",
      "isRequired": true,
      "choicesByUrl": {
        "url": "http://localhost:8080/api/tickername"
      },
      "name": "tickername",
      "title": "백테스트를 진행할 코인을 골라주세요",
      "description": "Please select all that apply."
    }
  ],
  "showQuestionNumbers": false
};

const survey = new Survey.Model(json);
survey.onComplete.add((sender, options) => {
    console.log(JSON.stringify(sender.data, null, 3));
});

$("#surveyElement").Survey({ model: survey });
})();