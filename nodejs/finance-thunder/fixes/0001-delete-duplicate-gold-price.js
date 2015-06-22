// delete duplicate gold price
// run in mongo shell
// $ mongo load script.js
//
// 刪除和目前這筆相同的上一筆和下一筆資料



// main program

{
    var db = connect('localhost/finance-thunder');

    var prev = {
        sell: 0,
        buy: 0
    };
    var current = {};
    var idsToRemove = [];
    print('All records: ' + db.golds.count());
    db.golds.find().forEach(function(gold) {
        current = gold;

        var delMsg = '';
        if (prev._id !== undefined) {
            if (prev.buy === current.buy && prev.sell === current.sell) {
                idsToRemove.push(current._id);
                delMsg = 'Should be deleted!';
            }
        }
        print(current._id, prev.buy, prev.sell, current.buy, current.sell, delMsg);

        prev = current;
    });
    db.golds.remove({_id: {$in: idsToRemove}});
    print('Deleted records: ' + idsToRemove.length);

    print("Info: Delete duplicate gold pirce finished.");
}
