import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TourneyListComponent } from './tourney-list.component';

describe('TourneyListComponent', () => {
  let component: TourneyListComponent;
  let fixture: ComponentFixture<TourneyListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TourneyListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TourneyListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
