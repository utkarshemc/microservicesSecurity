import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { OKTA_CONFIG, OktaAuthModule } from '@okta/okta-angular';
import { RouterTestingModule } from '@angular/router/testing';
describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  const oktaConfig = {
    issuer: 'https://not-real.okta.com',
    clientId: 'fake-client-id',
    redirectUri: 'http://localhost:4200'
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent],
      imports: [
        RouterTestingModule,
        OktaAuthModule
      ],
      providers: [{provide: OKTA_CONFIG, useValue: oktaConfig}]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
