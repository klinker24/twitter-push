/*
*
*  Push Notifications codelab
*  Copyright 2015 Google Inc. All rights reserved.
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      https://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License
*
*/

'use strict';

console.log('Started', self);

self.addEventListener('install', function(event) {
  self.skipWaiting();
  console.log('Installed', event);
});

self.addEventListener('activate', function(event) {
  console.log('Activated', event);
});

self.addEventListener('push', function(event) {
  console.log('Push message', event);

  event.waitUntil(
    fetch('notification_data.json').then(function(response) {  
        return response.json().then(function(notification) {  
          var title = notification.data.title;
          var message = notification.data.message;
          var icon = notification.data.icon;

          return self.registration.showNotification(title, {
            body: message,
            icon: icon,
            data: {
              url: notification.data.url
            }
          });
        });  
      }).catch(function(err) {  
        console.log('Fetch Error :-S', err);  
      })
    );
});

self.addEventListener('notificationclick', function(event) {
  console.log('Notification click: tag', event.notification.tag);
  // Android doesn't close the notification when you click it
  // See http://crbug.com/463146
  event.notification.close();
  var url = event.notification.data.url;
  // Check if there's already a tab open with this URL.
  // If yes: focus on the tab.
  // If no: open a tab with the URL.
  event.waitUntil(
    clients.matchAll({
      type: 'window'
    })
    .then(function(windowClients) {
      if (clients.openWindow) {
        return clients.openWindow("/redirect.php?url=" + url);
      }
    })
  );
});
