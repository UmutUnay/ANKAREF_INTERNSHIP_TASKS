import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseEventsComponent } from './close-events.component';

describe('CloseEventsComponent', () => {
  let component: CloseEventsComponent;
  let fixture: ComponentFixture<CloseEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CloseEventsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CloseEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
