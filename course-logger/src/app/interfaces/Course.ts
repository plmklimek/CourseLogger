import { User } from './UserInterface';
export interface Course {
  id?: number;
  name: string;
  students?: User[];
  teacher?: User;
  teacherId: number;
}
