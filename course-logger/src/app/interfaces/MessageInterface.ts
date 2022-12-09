import { Observable } from "rxjs";

export interface Message{
    message:string | Observable<Message>;
}