const { it, expect } = require('@jest/globals');
const webCrawler = require('./index');

jest.mock('request');

describe('Web crawler', () => {
  afterAll(() => {
    jest.resetAllMocks();
  });

  it('webCrawler must return status code', () => {
    // expect(webCrawler.Crawler()).toBe('Status code: 200');
    request.mockImplementation((option, callback) => callback())
  });

});
