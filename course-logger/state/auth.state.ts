import { User } from "../interfaces/UserInterface";

export interface AuthState {
  user: User;
}
export const initialState: AuthState = {
  user: {} as User,
};
