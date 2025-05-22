import { Component } from '@angular/core';

@Component
({
  selector: 'related-links',
  templateUrl: './relatedLinks.component.html',
})

export class RelatedLinksComponent 
{

  onNavigate(link : string)
  {
    window.open(link);
  }
}