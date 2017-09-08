const CDP = require('chrome-remote-interface');

CDP(async(client) => {
    const {Network, Page} = client;
    
    Network.responseReceived(async ({requestId, response}) => {
        const {body, base64Encoded} = await Network.getResponseBody({requestId});
        console.log(body);
    });

    Page.loadEventFired(() => {
        // client.close();
    });
    Promise.all([
        Network.enable(),
        Page.enable()
    ]).then(() => {
        return Page.navigate({url: 'https://www.sample.com'});
    }).catch((err) => {
        console.error(`ERROR: ${err.message}`);
        client.close();
    });
}).on('error', (err) => {
    console.error(err);
});
