const backendLocation = 'http://localhost:8000'

let articleId = document.getElementById("articleId").getAttribute("value")
// let imageSection = document.getElementById("articleImage")
// let contentSection = document.getElementById("articleContent")
let currentArticle = document.getElementById("currentArticle")

fetch(`${backendLocation}/api/${articleId}/articles`)
    .then((response) => response.json())
    .then((body) => {

        renderArticle(body)

    })

function renderArticle(body) {

    // imageSection.setAttribute("src", body.imageUrl)
    let contentHtml = `<div class="articles" id="article${body.id}">\n`
    contentHtml += '<div type="hidden">' + body.id + '</div>'
    contentHtml += `<img src="${body.imageUrl}" class="card-img-top" alt="image">`
    contentHtml += '<p>' + body.content + '</p>\n'

    if (body.canEdit) {
        contentHtml += `<button class="btn btn-danger" onclick="deleteArticle(${body.id})">Изтриване</button>\n`
    }

    // contentSection.innerHTML += contentHtml
    currentArticle.innerHTML += contentHtml
}

// In POST and DELETE we have to add csrf manually. The csrf is placed in commons.html - head
const csrfHeaderName = document.getElementById('csrf').getAttribute('name')
const csrfHeaderValue = document.getElementById('csrf').getAttribute('value')

function deleteArticle (articleId) {
    fetch(`${backendLocation}/api/${articleId}/articles`, {
        method: 'DELETE',
        headers: {
            [csrfHeaderName]: csrfHeaderValue
        }
    }).then(res => {
        console.log(res)
        document.getElementById("article" + articleId).remove()
        history.go(-1)
    })
}