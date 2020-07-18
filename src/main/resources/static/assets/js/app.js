(function ($) {

    $(document).ready(function () {
        let filters = $('#filters');
        let results = $('#results');
        let more = $('#load-more');
        let page = 1;

        filters.find('button').click(function () {
            page = 1;
            results.addClass('d-none');

            let button = $(this);
            button.attr('disabled', 'disabled');

            let data = getFilterParams(filters, page);
            getQuestions(data)
                .then(items => {
                    if(!items.length) {
                        more.addClass('d-none');
                    }
                    else {
                        more.removeClass('d-none');
                    }

                    generateResults(items, results.find('> ul'), false);
                    results.removeClass('d-none');
                })
                .finally(() => button.removeAttr('disabled'));
        });

        more.find('button').click(function () {
            page++;

            let button = $(this);
            button.attr('disabled', 'disabled');

            let data = getFilterParams(filters, page);
            getQuestions(data)
                .then(items => {
                    if(!items.length) {
                        more.addClass('d-none');
                    }
                    else {
                        more.removeClass('d-none');
                    }

                    generateResults(items, results.find('> ul'), true)
                })
                .finally(() => button.removeAttr('disabled'));
        });
    });
})(jQuery);

function getFilterParams(filters, page) {
    let data = {page};

    if(filters.find('#q').val()) {
        data.q = filters.find('#q').val();
    }
    if(filters.find('#tagged').val()) {
        data.tagged = [filters.find('#tagged').val()];
    }
    if(filters.find('#from-date').val()) {
        data.fromDate = filters.find('#from-date').val() + " 00:00:00";
    }
    if(filters.find('#to-date').val()) {
        data.toDate = filters.find('#to-date').val() + " 00:00:00";
    }
    if(filters.find('#sort').val()) {
        data.sort = filters.find('#sort').val();
    }
    if(filters.find('#order').val()) {
        data.order = filters.find('#order').val();
    }
    if(filters.find('#page-size').val()) {
        data.pageSize = filters.find('#page-size').val();
    }

    return data;
}

function getQuestions(data) {
    return new Promise(function (resolve, reject) {
        jQuery
            .ajax({
                url: app.apiUrl + '/search',
                type: 'POST',
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
            })
            .done((response) => resolve(response.items.length ? response.items : []))
            .fail(() => reject([]));
    });
}

function generateResults(items, list, append) {
    console.log(list);
    if(!append) {
        list.html('');
    }

    if(items.length) {
        items.forEach(item => {
            let tags = '';
            item.tags.forEach(tag => tags += `<li>${tag}</li>`);

            list.append(`
                    <li class="q">
                        <div class="title">
                            <i class="fa fa-question-circle-o"></i>
                            <a href="${app.baseUrl + '/question/' + item.question_id}">${item.title}</a>
                        </div>
                        <div class="info">
                            <span class="score" title="Votes">
                                <i class="fa fa-thumbs-up"></i> ${item.score}
                            </span>
                            <span class="view-count" title="View Count">
                                <i class="fa fa-eye"></i> ${item.view_count}
                            </span>
                            <span class="answer-count" title="Answer Count">
                                <i class="fa fa-quote-left"></i> ${item.answer_count}
                            </span>
                            <span class="date">
                                <i class="fa fa-calendar"></i> ${item.date}
                            </span>
                        </div>
                        <div class="tags">
                            <span>Tags:</span>
                            <ul>${tags}</ul>
                        </div>
                    </li>
                `);
        })
    }
    else {
        list.append(`<li>No record found.</li>`)
    }
}