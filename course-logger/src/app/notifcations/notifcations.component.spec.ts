import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifcationsComponent } from './notifcations.component';

describe('NotifcationsComponent', () => {
  let component: NotifcationsComponent;
  let fixture: ComponentFixture<NotifcationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotifcationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotifcationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
