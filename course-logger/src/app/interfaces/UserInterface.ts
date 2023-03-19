import { Authority } from './Authority';

export interface User {
  id?: number;
  email: string;
  password: string;
  username?: string;
  name?: string;
  surname?: string;
  image?: string;
  base?: string;
  authorities?: Authority[];
  marks?: number[];
  imageBlob?: any;
}
