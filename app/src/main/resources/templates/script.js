const body = document.getElementById('body');
const main = document.getElementById('main');
const spot_id = document.getElementById('spot');
const formTag = document.getElementById('formTag');
const formAuthor = document.getElementById('formAuthor');
const formDate = document.getElementById('formDate');
const formImgUrl = document.getElementById('formImgUrl');
const formSaying = document.getElementById('formSaying');
const formSummary= document.getElementById('formSummary');
const formContent= document.getElementById('formContent');

const addButton = document.getElementById('add');
let saveButton = document.getElementById('save');
const cancelButton = document.getElementById('cancel');

$(document).ready(function () {

    // var svgObject = document.getElementById("mock");

    // var svgDoc = svgObject.contentDocument;

    // var svg = svgDoc.getElementById("spot_1");
    // console.log(svg);

    //use jquery functions to do some thing
    //svg.find("g path:first-child()").attr('fill', color);

    
    var svgDoc = $("#mock_html")[0].contentDocument;

    var mock_svg = $("#mock_svg" , svgDoc);
    var spots = {};
    var status= {};
    spots = $("svg > g",mock_svg);

    spots.each(function(index,element) {
        status[index] = 0; // toate-s gri
        updateSpotToServer(index + 1 , 0);

        element.addEventListener("click", function() {
            console.log("spot " , element);
        });
        //element.addEventListener("click", mySecondFunction);
    });

    

    updateSpotToServer(5 , 1);
    updateSpotToServer(67 , );

    function updateSpotToServer(id , newStatus) {
        //stabilim statusul parcarii id-ului primit ca parametru
        if(newStatus == 0) {

            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "green" );
            }
            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "green" );
            }
            if(status[id-1] == 3) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "green" );
            }

            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "green" );
            }
            
            status[id -1] = 0;
        }
        if(newStatus == 1) {

            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "red" );
            }
            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "red" );
            }
            if(status[id-1] == 3) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "red" );
            }
            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "red" );
            }

            status[id -1] = 1;
        }
        if(newStatus == 2) {

            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "gray" );
            }
            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "gray" );
            }
            if(status[id-1] == 3) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "gray" );
            }
            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "gray" );
            }
            status[id -1] = 2;
        }
        if(newStatus == 3) {

            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "orange" );
            }
            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "orange" );
            }
            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "orange" );
            }
            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "orange" );
            }
            status[id -1] = 3;
        }

        //TODO in cazul in care avem variabila locala
    }

    function changeColor(elem , color) {
        $(elem).find("[id^='overlay']").find(".st3").css("fill" , red);
        $(elem).find("[id^='overlay']").find(".st3").css({ opacity: 1 });   
    }
});

// // Fetch the articles list
// function getArticlesFromServer() {
//     fetch('http://localhost:8080/parking')
//         .then(function (response) {
//             // Trasform server response to plain object
//             response.json().then(function (articles) {
//                 renderArticles(articles);
//             });
//         });
// };

// // Add article
// function addSpotToServer() {
//     // creat post object
//     const postObject = {
//         title: formTitle.value,
//         tag: formTag.value,
//         author: formAuthor.value,
//         date: formDate.value,
//         imgUrl: formImgUrl.value,
//         saying: formSaying.value,
//         summary: formSummary.value,
//         content: formContent.value
//     }
//     // Call post request to add a new article
//     fetch('http://localhost:3000/articles', {
//         method: 'post',
//         headers: {
//             "Content-type": "application/json"
//         },
//         body: JSON.stringify(postObject)
//     }).then(function () {
//         // Get the new article list
//         getSpotFromServer();

//         // Reset Form
//         resetModal();

//         // Close Modal
//         closeModal();
//     });
// }

// // Update article
// function updateSpotToServer(id) {
//     // creat put object
//     const putObject = {
//         id: spot_id.value,
//         status: spot_status.value,
//         email: spot_email.value
//     }
//     // Call put request to update the article
//     fetch(`http://localhost:8080/parking/${id}`, {
//         method: 'PUT',
//         headers: {
//             "Content-type": "application/json"
//         },
//         body: JSON.stringify(putObject)
//     }).then(function () {
//         getSpotFromServer();
//         resetForm();
//         closeModal();
//     });
// }

// // Copy edited article information to form and add event listener on update button
// function openModal0() {

//     // clear all events save button events
//     clearSaveButtonEvents();
//     saveButton.addEventListener('click', function () {
//         addSpotToServer()
//     });

//     body.className = 'show-modal';
// }

// // Copy edited article information to form and add event listener on update button
// function openModal1(article) {
//     // Copy article information to form
//     formTitle.value = article.title;
//     formTag.value = article.tag;
//     formAuthor.value = article.author;
//     formDate.value = article.date;
//     formImgUrl.value = article.imgUrl;
//     formSaying.value = article.saying;
//     formSummary.value = article.summary;
//     formContent.value = article.content;

//     // clear all events update button events
//     clearSaveButtonEvents();

//     saveButton.addEventListener('click', function () {
//         updateArticleToServer(article.id)
//     });

//     body.className = 'show-modal';
// }

// // Remove articles list if exist
// function removeOldArticlesFromDOM () {
//     while (main.firstChild) {
//         main.removeChild(main.firstChild);
//     }
// }

// function createArticleDOMNode(article) {

//     // Title
//     let title = document.createElement('h2');
//     title.className = "title";
//     title.textContent = article.title;

//     // Tag
//     let tag = document.createElement('li');
//     tag.className = "info__item";
//     tag.textContent = article.tag;

//     // Author
//     let author = document.createElement('span');
//     author.className = "info__mark";
//     author.textContent = article.author;

//     let authorContainer = document.createElement('li');
//     authorContainer.className = "info__item";
//     authorContainer.textContent = 'Added By ';
//     authorContainer.appendChild(author)

//     // Date
//     let date = document.createElement('li');
//     date.className = "info__item";
//     date.textContent = article.date;

//     // Information container
//     let infoContainer = document.createElement('ul');
//     infoContainer.className = "info__container";
//     infoContainer.appendChild(tag);
//     infoContainer.appendChild(authorContainer);
//     infoContainer.appendChild(date);

//     // Edit button
//     let editButton = document.createElement('button');
//     editButton.className = "actions__btn";
//     // Add event on edit button and pass article object to populate the form  more at https://stackoverflow.com/questions/256754/how-to-pass-arguments-to-addeventlistener-listener-function
//     editButton.addEventListener('click', function () {
//         openEditModal(article);
//     });
//     editButton.textContent = 'Edit';

//     // Delete button
//     let deleteButton = document.createElement('button');
//     deleteButton.className = "actions__btn";
//     // Add event on delete button and pass article id to delete it form server more at https://stackoverflow.com/questions/256754/how-to-pass-arguments-to-addeventlistener-listener-function
//     deleteButton.addEventListener('click', function () {
//         deleteArticleFromServer(article.id);
//     });
//     deleteButton.textContent = 'Delete';

//     // Buttons container
//     let buttonsContainer = document.createElement('div');
//     buttonsContainer.className = "actions__container";
//     buttonsContainer.appendChild(editButton);
//     buttonsContainer.appendChild(deleteButton);

//     // Image
//     let img = document.createElement('img');
//     img.src = article.imgUrl;

//     // Paragraph
//     let paragraph = document.createElement('p');
//     paragraph.textContent = article.content;

//     // Paragraph container
//     let paragraphContainer = document.createElement('div');
//     paragraphContainer.className = "content__container";
//     paragraphContainer.appendChild(paragraph);

//     // Readme
//     let readme = document.createElement('button');
//     readme.className = "button";
//     readme.textContent = "Read More";

//     // Paragraph container
//     let readmeContainer = document.createElement('div');
//     readmeContainer.className = "readme__container";
//     readmeContainer.appendChild(readme);

//     // Append all article nodes to container
//     let articleNode = document.createElement('article');
//     articleNode.appendChild(title);
//     articleNode.appendChild(infoContainer);
//     articleNode.appendChild(buttonsContainer);
//     articleNode.appendChild(img);
//     articleNode.appendChild(paragraphContainer);
//     articleNode.appendChild(readmeContainer);

//     return articleNode;
// }

// // Create DOM objects and append them to DOM
// function renderArticles(articles) {
    
//     removeOldArticlesFromDOM()

//     // Create and append tags
//     for (let i = 0; i < articles.length; i++) {
//         let articleDOMNode = createArticleDOMNode(articles[i]);
//         main.appendChild(articleDOMNode);
//     }
// }

// // Reset form values
// function resetForm() {
//     formTitle.value = '';
//     formTag.value = '';
//     formAuthor.value = '';
//     formDate.value = '';
//     formImgUrl.value = '';
//     formSaying.value = '';
//     formSummary.value = '';
//     formContent.value = '';
// }
// //  Remove Save Button to clear events more at https://stackoverflow.com/questions/9251837/how-to-remove-all-listeners-in-an-element
// function clearSaveButtonEvents() {
//     let newUpdateButton = saveButton.cloneNode(true);
//     saveButton.parentNode.replaceChild(newUpdateButton, saveButton);
//     saveButton = document.getElementById('save');
// }

// // Close modal
// function closeModal() {
//     body.className = '';
// }

// // Add event listener on Add button
// addButton.addEventListener('click', openAddModal);
// // Add event listener on Cancel button
// cancelButton.addEventListener('click', closeModal);

// // Get all articles
// getArticlesFromServer();