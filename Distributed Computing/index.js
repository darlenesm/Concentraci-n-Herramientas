const request = require('request');
const cheerio = require('cheerio');
const fs = require('fs');

request('https://news.ycombinator.com/news', (error, response, body) => {
  if (error) throw new Error(`An error has ocurred: ${error}`);
  console.log(`Status code: ${response.statusCode}\n`);

  let $ = cheerio.load(body);

  $('tr.athing:has(td.votelinks)').each(function( index ) {
    let title = $(this).find('td.title > a').text().trim();
    let link = $(this).find('td.title > a').attr('href');
    console.log(`${title},${link}\n`);
    fs.appendFileSync('hackernews.txt', `${title},${link}\n`);
  });
});
