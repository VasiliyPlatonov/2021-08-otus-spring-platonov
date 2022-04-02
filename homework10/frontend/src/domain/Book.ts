import {Author} from "./Author";
import {Genre} from "./Genre";

export interface Book {
    id: number;
    title: number;
    authors: Author[];
    genres: Genre[]
}