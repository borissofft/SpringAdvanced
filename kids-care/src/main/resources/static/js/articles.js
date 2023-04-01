const backendLocation = 'http://localhost:8000'

let articleId = document.getElementById("articleId").getAttribute("value")
let imageSection = document.getElementById("articleImage")
let contentSection = document.getElementById("articleContent")

fetch(`${backendLocation}/api/${articleId}/articles`)
    .then((response) => response.json())
    .then((body) => {

        renderArticle(body)

    })

function renderArticle(body) {

    imageSection.setAttribute("src", body.imageUrl)
    let contentHtml = '<p>' + body.content + '</p>\n'

    contentSection.innerHTML += contentHtml
}

