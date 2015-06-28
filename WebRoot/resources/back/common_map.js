/**
 * 
 */
function StringBuffer() {
    this.__strings__ = new Array;
}

StringBuffer.prototype.append = function(str) {
    this.__strings__.push(str);
};

StringBuffer.prototype.toString = function() {
    return this.__strings__.join("");
};

function Map(linkItems) {
    this.current = undefined;
    this._size = 0;
    if (linkItems === false) {
        this.disableLinking();
    }
}

function getExportCSVFileName(fileName) {
    return fileName + "_" + getCurrentDateByPattern('yyyyMMdd') + ".csv";
}

function getCurrentDateByPattern(pattern) {
    return formatDate(new Date(), pattern);
}

Map.noop = function() {
    return this;
};
Map.illegal = function() {
    throw new Error("异常操作。");
};

Map.from = function(obj, foreignKeys) {
    var map = new Map;
    for ( var prop in obj) {
        if (foreignKeys || obj.hasOwnProperty(prop)) {
            map.put(prop, obj[prop]);
        }
    }
    return map;
};

Map.prototype.disableLinking = function() {
    this.link = Map.noop;
    this.unlink = Map.noop;
    this.disableLinking = Map.noop;
    this.next = Map.illegal;
    this.key = Map.illegal;
    this.value = Map.illegal;
    this.clear = Map.illegal;
    return this;
};

Map.prototype.hash = function(value) {
    return (typeof value)
            + ' '
            + (value instanceof Object ? (value.__hash || (value.__hash = ++arguments.callee.current))
                    : value.toString());
};

Map.prototype.size = function() {
    return this._size;
};

Map.prototype.hash.current = 0;

Map.prototype.get = function(key) {
    var item = this[this.hash(key)];
    return item === undefined ? undefined : item.value;
};

Map.prototype.put = function(key, value) {
    var hash = this.hash(key);
    if (this[hash] === undefined) {
        var item = {
            key : key,
            value : value
        };
        this[hash] = item;
        this.link(item);
        ++this._size;
    } else {
        this[hash].value = value;
    }
    return this;
};

Map.prototype.remove = function(key) {
    var hash = this.hash(key);
    var item = this[hash];
    if (item !== undefined) {
        --this._size;
        this.unlink(item);
        delete this[hash];
    }
    return this;
};

Map.prototype.clear = function() {
    while (this._size) {
        this.remove(this.key());
    }
    return this;
};

Map.prototype.link = function(item) {
    if (this._size == 0) {
        item.prev = item;
        item.next = item;
        this.current = item;
    } else {
        item.prev = this.current.prev;
        item.prev.next = item;
        item.next = this.current;
        this.current.prev = item;
    }
};

Map.prototype.unlink = function(item) {
    if (this._size == 0) {
        this.current = undefined;
    } else {
        item.prev.next = item.next;
        item.next.prev = item.prev;
        if (item === this.current) {
            this.current = item.next;
        }
    }
};

Map.prototype.next = function() {
    this.current = this.current.next;
    return this;
};

Map.prototype.key = function() {
    return this.current.key;
};

Map.prototype.value = function() {
    return this.current.value;
};