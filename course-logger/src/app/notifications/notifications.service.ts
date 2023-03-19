import { Injectable } from '@angular/core';
import { Observable, ObservableLike, of, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  text = new Subject<string>();
  
  constructor() { }

  setText(text: string){
    this.text.next(text);
    setTimeout(() => this.text.next(''), 3 * 1000);
  }
  
  getText():Observable<string>{
    return this.text.asObservable();
  }
}
