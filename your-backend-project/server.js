const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql');

const app = express();
app.use(bodyParser.json());

const db = mysql.createConnection({
    host: config.DB_HOST,
    user: config.DB_USER,
    password: config.DB_PASSWORD,
    database: config.DB_NAME
});

db.connect(err => {
    if (err) throw err;
    console.log('Connected to database');
});

// Add Data
app.post('/add', (req, res) => {
    const { data } = req.body;
    const sql = 'INSERT INTO your_table (column_name) VALUES (?)';
    db.query(sql, [data], (err, result) => {
        if (err) throw err;
        res.send('Data added');
    });
});

// Remove Data
app.delete('/remove/:id', (req, res) => {
    const sql = 'DELETE FROM your_table WHERE id = ?';
    db.query(sql, [req.params.id], (err, result) => {
        if (err) throw err;
        res.send('Data removed');
    });
});

// Update Data
app.put('/update/:id', (req, res) => {
    const { data } = req.body;
    const sql = 'UPDATE your_table SET column_name = ? WHERE id = ?';
    db.query(sql, [data, req.params.id], (err, result) => {
        if (err) throw err;
        res.send('Data updated');
    });
});

// Read Data
app.get('/read', (req, res) => {
    const sql = 'SELECT * FROM your_table';
    db.query(sql, (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

app.listen(3000, () => {
    console.log('Server running on port 3000');
});
