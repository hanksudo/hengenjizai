/**
 * SETUP
 **/
  var app = app || {};



/**
 * MODELS
 **/
  app.Forgot = Backbone.Model.extend({
    url: '/login/forgot/',
    defaults: {
      success: false,
      errors: [],
      errfor: {},
      email: '',
    }
  });



/**
 * VIEWS
 **/
  app.ForgotView = Backbone.View.extend({
    el: '#forgot',
    template: _.template( $('#tmpl-forgot').html() ),
    events: {
      'submit form': 'preventSubmit',
      'keypress [name="email"]': 'forgotOnEnter',
      'click .btn-forgot': 'forgot'
    },
    initialize: function() {
      this.model = new app.Forgot();
      this.model.bind('change', this.render, this);
      this.render();
    },
    render: function() {
      this.$el.html(this.template( this.model.attributes ));
      this.$el.find('[name="email"]').focus();
      return this;
    },
    preventSubmit: function(event) {
      event.preventDefault();
    },
    forgotOnEnter: function(event) {
      if (event.keyCode != 13) return;
      event.preventDefault();
      this.forgot();
    },
    forgot: function() {
      this.$el.find('.btn-forgot').attr('disabled', true);
      
      this.model.save({
        email: this.$el.find('[name="email"]').val()
      });
    }
  });



/**
 * BOOTUP
 **/
  $(document).ready(function() {
    app.forgotView = new app.ForgotView();
  });


