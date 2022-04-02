import {Book} from '../domain/Book';

class Api {
    public async getBooks(): Promise<Book[]> {
        const response = await fetch('/books');
        return response.json();
    }
}

export const BookApi = new Api();