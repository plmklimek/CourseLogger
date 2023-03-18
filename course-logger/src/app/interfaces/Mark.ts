import { Course } from './Course';
import {User} from './UserInterface';
export interface Mark {
    id: number,
    teacher: User,
    student: User,
    course: Course,
    mark:number
}