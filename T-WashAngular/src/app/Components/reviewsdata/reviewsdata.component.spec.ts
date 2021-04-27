import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewsdataComponent } from './reviewsdata.component';

describe('ReviewsdataComponent', () => {
  let component: ReviewsdataComponent;
  let fixture: ComponentFixture<ReviewsdataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewsdataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewsdataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
