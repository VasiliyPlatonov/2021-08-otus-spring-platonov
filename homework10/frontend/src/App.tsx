import React, {useEffect, useState} from 'react';
import './App.css';
import {BookApi} from './api/BookApi';
import {DataGrid, GridColDef, GridRowsProp} from '@mui/x-data-grid';
import Container from '@mui/material/Container';


export const App: React.FC = () => {

    const [rows, setRows] = useState<GridRowsProp>([]);

    const columns: GridColDef[] = [
        {field: 'bookId', headerName: 'Id', width: 50},
        {field: 'bookTitle', headerName: 'Title', width: 400},
        {field: 'bookAuthors', headerName: 'Authors', width: 400},
        {field: 'bookGenres', headerName: 'Genres', width: 250},
    ];

    useEffect(() => {
        BookApi.getBooks()
            .then(books => {
                console.log('books: ', books);

                const rows = books.map(b => {

                    const authors = b.authors.map(a => `${a.firstName} ${a.lastName}`)
                    const genres = b.genres.map(g => `${g.name}`)

                    return {
                        id: b.id,
                        bookId: b.id,
                        bookTitle: b.title,
                        bookAuthors: authors,
                        bookGenres: genres,
                    }
                });

                setRows(rows);
            });
    }, []);

    return (
        <Container maxWidth="lg">
            <DataGrid autoHeight={true} rows={rows} columns={columns}/>
        </Container>
    );
}

export default App;
