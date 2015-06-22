/**
 * SETUP
 **/
  var app = app || {};



/**
 * MODELS
 **/
  app.AdminGroup = Backbone.Model.extend({
    idAttribute: '_id',
    url: function() {
      return '/admin/admin-groups/'+ this.id +'/';
    }
  });
  
  app.Delete = Backbone.Model.extend({
    idAttribute: '_id',
    defaults: {
      success: false,
      errors: [],
      errfor: {}
    },
    url: function() {
      return '/admin/admin-groups/'+ app.mainView.model.id +'/';
    }
  });
  
  app.Details = Backbone.Model.extend({
    idAttribute: '_id',
    defaults: {
      success: false,
      errors: [],
      errfor: {},
      name: ''
    },
    url: function() {
      return '/admin/admin-groups/'+ app.mainView.model.id +'/';
    },
    parse: function(response) {
      if (response.adminGroup) {
        app.mainView.model.set(response.adminGroup);
        delete response.adminGroup;
      }
      return response;
    }
  });
  
  app.Permissions = Backbone.Model.extend({
    idAttribute: '_id',
    defaults: {
      success: false,
      errors: [],
      errfor: {},
      permissions: [],
      newPermission: ''
    },
    url: function() {
      return '/admin/admin-groups/'+ app.mainView.model.id +'/permissions/';
    },
    parse: function(response) {
      if (response.adminGroup) {
        app.mainView.model.set(response.adminGroup);
        delete response.adminGroup;
      }
      return response;
    }
  });



/**
 * VIEWS
 **/
  app.HeaderView = Backbone.View.extend({
    el: '#header',
    template: _.template( $('#tmpl-header').html() ),
    initialize: function() {
      this.model = app.mainView.model;
      this.model.on('change', this.render, this);
      this.render();
    },
    render: function() {
      this.$el.html(this.template( this.model.attributes ));
    }
  });
  
  
  app.DetailsView = Backbone.View.extend({
    el: '#details',
    template: _.template( $('#tmpl-details').html() ),
    events: {
      'click .btn-update': 'update'
    },
    initialize: function() {
      this.model = new app.Details();
      this.syncUp();
      app.mainView.model.bind('change', this.syncUp, this);
      
      this.model.on('change', this.render, this);
      this.render();
    },
    syncUp: function() {
      this.model.set({
        _id: app.mainView.model.id,
        name: app.mainView.model.get('name')
      });
    },
    render: function() {
      //render
      this.$el.html(this.template( this.model.attributes ));
      
      //set input values
      for(var key in this.model.attributes) {
        this.$el.find('[name="'+ key +'"]').val(this.model.attributes[key]);
      }
    },
    update: function() {
      this.model.save({
        name: this.$el.find('[name="name"]').val()
      });
    }
  });
  
  app.DeleteView = Backbone.View.extend({
    el: '#delete',
    template: _.template( $('#tmpl-delete').html() ),
    events: {
      'click .btn-delete': 'delete',
    },
    initialize: function() {
      this.model = new app.Delete({ _id: app.mainView.model.id });
      this.model.on('change', this.render, this);
      this.render();
    },
    render: function() {
      this.$el.html(this.template( this.model.attributes ));
    },
    delete: function() {
      if (confirm('Are you sure?')) {
        this.model.destroy({
          success: function(model, response, options) {
            if (response.success) {
              location.href = '/admin/admin-groups/';
            }
            else {
              app.deleteView.model.set(response);
            }
          }
        });
      }
    }
  });
  
  app.PermissionsView = Backbone.View.extend({
    el: '#permissions',
    template: _.template( $('#tmpl-permissions').html() ),
    events: {
      'click .btn-add': 'add',
      'click .btn-allow': 'allow',
      'click .btn-deny': 'deny',
      'click .btn-delete': 'delete',
      'click .btn-set': 'savePermissions'
    },
    initialize: function() {
      this.model = new app.Permissions();
      this.syncUp();
      app.mainView.model.bind('change', this.syncUp, this);
      
      this.model.on('change', this.render, this);
      this.render();
    },
    syncUp: function() {
      this.model.set({
        _id: app.mainView.model.id,
        permissions: app.mainView.model.get('permissions')
      });
    },
    render: function() {
      //render
      this.$el.html(this.template( this.model.attributes ));
      
      //set input values
      for(var key in this.model.attributes) {
        this.$el.find('[name="'+ key +'"]').val(this.model.attributes[key]);
      }
    },
    add: function(event) {
      //validate
      var newPermission = this.$el.find('[name="newPermission"]').val().trim();
      if (!newPermission) {
        alert('Please enter a name.');
        return;
      }
      else {
        var alreadyAdded = false;
        _.each(this.model.get('permissions'), function(permission) {
          if (newPermission == permission.name) {
            alreadyAdded = true;
          }
        });
        if (alreadyAdded) {
          alert('That name already exists.');
          return;
        }
      }
      
      //add item
      this.model.get('permissions').push({ name: newPermission, permit: true });
      
      //sort
      var sorted = this.model.get('permissions');
      sorted.sort(function(a, b) {
        return a.name.toLowerCase() > b.name.toLowerCase();
      });
      this.model.set('permissions', sorted);
      
      //re-render
      this.render();
    },
    allow: function(event) {
      var idx = this.$el.find('.btn-allow').index(event.currentTarget);
      this.model.get('permissions')[idx].permit = true;
      this.render();
    },
    deny: function(event) {
      var idx = this.$el.find('.btn-deny').index(event.currentTarget);
      this.model.get('permissions')[idx].permit = false;
      this.render();
    },
    delete: function(event) {
      if (confirm('Are you sure?')) {
        var idx = this.$el.find('.btn-delete').index(event.currentTarget);
        this.model.get('permissions').splice(idx, 1);
        this.render();
      }
    },
    savePermissions: function() {
      this.model.save();
    }
  });
  
  app.MainView = Backbone.View.extend({
    el: '.page .container',
    initialize: function() {
      app.mainView = this;
      
      //setup model
      this.model = new app.AdminGroup( JSON.parse($('#data-record').html()) );
      
      //sub views
      app.headerView = new app.HeaderView();
      app.detailsView = new app.DetailsView();
      app.deleteView = new app.DeleteView();
      app.permissionsView = new app.PermissionsView();
    }
  });



/**
 * BOOTUP
 **/
  $(document).ready(function() {
    app.mainView = new app.MainView();
  });


